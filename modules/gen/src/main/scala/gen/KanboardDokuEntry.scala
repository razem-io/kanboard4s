package io.razem.kanboard4s
package gen

import ujson.Value

import scala.util.matching.Regex


object KanboardDokuEntry {
  case class KanboardParameter(name: String, foundType: String, optional: Boolean) {
    val pretty: String = s"$name: ${if(optional)"Option[" + foundType + "] = None" else foundType}"

    val jsonParamWrapper: String = jsonParamWrapper(name)

    def jsonParamWrapper(name: String): String = foundType match {
      case "Alphanumeric string" => s"StringParam($name)"
      case "String" => s"StringParam($name)"
      case "Int" => s"IntParam($name)"
      case "Array[String]" => s"ArrayStringParam($name)"
      case "Array[Int]" => s"ArrayIntParam($name)"
      case "Boolean" => s"BooleanParam($name)"
    }
  }

  val requestParamsRegex: Regex = """-\s+\*\*(\w*)\*\*([\s\S]*?)\(([\s\S]*?)\)""".r
}

case class KanboardDokuEntry(methodName: String, docu: String, request: String, response: String, responseSuccess: String, responseFailure: String, packageName: String, maybeException: Option[Exception] = None) {
  import KanboardDokuEntry._

  //  def requestParams: Set[String] = Try(ujson.read(request).obj("params")).map(_.obj.keys.toSet).getOrElse(Set.empty)
  def requestParams: List[KanboardParameter] = requestParamsRegex.findAllMatchIn(docu).map { m =>
    val name = m.group(1)
    // val comment = m.group(2)
    val additionalInfo = m.group(3).split(',').map(_.trim)

    val foundType = additionalInfo.head.capitalize match {
      case "Integer" => "Int"
      case "Integer array" => "Array[Int]"
      case "[]string" => "Array[String]"
      case "Alphanumeric string" => "String"
      case x => x
    }
    val optional = additionalInfo.lift(1).exists(_.contains("optional"))

    KanboardParameter(name, foundType, optional)
  }.toList

  val packageHeader: String = s"package $packageName"
  val imports: String = """
                          |import model.kanboard.api.KanboardApiCall
                          |import model.kanboard.api.JsonRPCRequest._
                          |""".stripMargin

  def docuComment: String = docu.linesIterator.map(l => "* " + l).mkString("/**\n" , "\n", "\n**/")

  def className: String = s"Kanboard_$methodName"
  def classNameRequest: String = s"Kanboard_Request_$methodName"
  def classNameResponse: String = s"Kanboard_Response_$methodName"
  def caseClassRequest: String = {
    /**
     * Seq("username" -> StringParam(username), "password" -> StringParam(password)) ++
    Seq(
      name.map("name" -> StringParam(_)), email.map("email" -> StringParam(_)), role.map("role" -> StringParam(_))
    ).flatten
     */
    s"""
       |case class $classNameRequest(${requestParams.map(_.pretty).mkString(", ")}) extends KanboardApiCall[$classNameResponse] {
       |  override val rpcMethodName: String = "$methodName"
       |
       |  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = ${
      val (optional, nonOptional) = requestParams.partition(_.optional)

      val s1 = if(nonOptional.nonEmpty) nonOptional.map(p => "\"" + p.name + "\"" + " -> " + p.jsonParamWrapper).mkString("Seq(", ", ", ")") else ""
      val s2 = if(nonOptional.nonEmpty && optional.nonEmpty) " ++ " else ""
      val s3 = if(optional.nonEmpty) optional.map(p => p.name + ".map(\"" + p.name + "\"" + " -> " + p.jsonParamWrapper("_") + ")").mkString("Seq(", ", ", ").flatten") else ""
      val s4 = if(nonOptional.isEmpty && optional.isEmpty) "Seq()" else ""

      s1 + s2 + s3 +  s4
    }
       |}
       |""".stripMargin
  }

  def caseClassResponse: String = {
    if(maybeException.exists(_.responseParameters.nonEmpty)) {
      s"""case class $classNameResponse(${maybeException.get.responseParameters.mkString(", ")})""".stripMargin
    } else if(responseSuccess.contains("Dictionary of")) {
      s"""case class $classNameResponse(result: Map[String, String])""".stripMargin
    } else {
      val r: Value = ujson.read(response).obj("result")

      matchResult(r) + "\n"
    }
  }

  def objectResponse: String = genJsonFormatObject(classNameResponse)

  def matchResult(r: Value, className: String = classNameResponse, returnResultClass: Boolean = true): String = {
    var additionalStuff: String = ""

    val s = if(r.numOpt.isDefined) {
      s"""case class $className(result: Int)""".stripMargin
    } else if(r.boolOpt.isDefined) {
      s"""case class $className(result: Boolean)""".stripMargin
    } else if(r.objOpt.isDefined) {
      val params: Map[String, String] = r.obj.map{ t => val (name, value) = t
        if(value.strOpt.isDefined) name -> "String"
        else if(value.objOpt.isDefined) name -> {
          val nestedClassName = classNameResponse + "_" + name.capitalize

          additionalStuff += "\n" + matchResult(value, className = nestedClassName, returnResultClass = false)

          nestedClassName
        }
        else if(value.isNull) name -> "Option[String]"
        else throw new NotImplementedError(value.toString())
      }.toMap

      val resultClassName = className + {
        if(returnResultClass) "_Result" else ""
      }
      val s1 = s"""case class $resultClassName(${params.map(t => t._1 + ": " + t._2).mkString(", ")})
         |${genJsonFormatObject(resultClassName)}""".stripMargin
      if(returnResultClass)
         s"""$s1
           |case class $className(result: $resultClassName)""".stripMargin
      else
        s1
    } else if(r.arrOpt.isDefined) {
      val entryClassName = className + "_Entries"
      val companionClass = matchResult(r.arr.head, entryClassName, returnResultClass = false)

      companionClass + "\n" +
        s"""case class $className(result: Array[$entryClassName])""".stripMargin
    } else if(r.strOpt.isDefined) {
      s"""case class $className(result: String)""".stripMargin
    } else {
      throw new NotImplementedError(r.toString())
    }

    s + additionalStuff
  }

  def genJsonFormatObject(objectName: String): String = s"""object $objectName {
                                                   |  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
                                                   |  implicit val rw: RW[$objectName] = macroRW
                                                   |}""".stripMargin
}
