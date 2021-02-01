package io.razem.kanboard4s
package gen

import ujson.Value

import scala.util.Try

case class KanboardDokuEntry(methodName: String, docu: String, request: String, response: String, packageName: String) {
  def requestParams: Set[String] = Try(ujson.read(request).obj("params")).map(_.obj.keys.toSet).getOrElse(Set.empty)

  val packageHeader: String = s"package $packageName"
  val imports: String = """
                          |import model.kanboard.api.KanboardApiCall
                          |""".stripMargin

  def docuComment: String = docu.linesIterator.map(l => "* " + l).mkString("/**\n" , "\n", "\n**/")

  def className: String = s"Kanboard_$methodName"
  def classNameRequest: String = s"Kanboard_Request_$methodName"
  def classNameResponse: String = s"Kanboard_Response_$methodName"
  def caseClassRequest: String =
    s"""
       |case class $classNameRequest(${requestParams.map(_ + ": String").mkString(", ")}) extends KanboardApiCall[$classNameResponse] {
       |  override val rpcMethodName: String = "$methodName"
       |
       |  override val rpcParameters: Seq[(String, String)] = Seq(${requestParams.map(p => "\"" + p + "\"" + " -> " + p).mkString(", ")})
       |}
       |""".stripMargin

  def caseClassResponse: String = {
    val r: Value = ujson.read(response).obj("result")

    matchResult(r) + "\n"
  }

  def objectResponse: String = genJsonFormatObject(classNameResponse)

  def matchResult(r: Value, className: String = classNameResponse): String = {
    if(r.numOpt.isDefined) {
      s"""case class $className(result: Int)""".stripMargin
    } else if(r.boolOpt.isDefined) {
      s"""case class $className(result: Boolean)""".stripMargin
    } else if(r.objOpt.isDefined) {
      val params: Map[String, String] = r.obj.map{ t => val (name, value) = t
        if(value.strOpt.isDefined) name -> "String"
        else if(value.isNull) name -> "Option[String]"
        else throw new NotImplementedError()
      }.toMap
      s"""case class $className(${params.map(t => t._1 + ": " + t._2).mkString(", ")})""".stripMargin
    } else if(r.arrOpt.isDefined) {
      val entryClassName = className + "_Entries"
      val companionClass = matchResult(r.arr.head, entryClassName)
      val companionObject = genJsonFormatObject(entryClassName)

      companionClass + "\n" +
      companionObject + "\n" +
        s"""case class $className(result: Array[$entryClassName])""".stripMargin
    }  else {
      throw new NotImplementedError(r.toString())
    }
  }

  def genJsonFormatObject(objectName: String): String = s"""object $objectName {
                                                   |  implicit val jsonFormat:Format[$objectName] = Json.format[$objectName]
                                                   |}""".stripMargin
}
