package io.razem.kanboard4s
package gen

import better.files._

import java.nio.charset.StandardCharsets
import scala.util.matching.Regex


object GenClasses {

  """(\w*)\n-{3,}([\s\S]*?)\nRequest example:[^{]*([\s\S]*?)\nResponse example:[\s\S]*?(\{[^}]*})"""

  val docRegex1: Regex = """(\w*)(\r\n|\r|\n)-{3,}([\s\S]*?)\nRequest example:[^{]*([\s\S]*?)\nResponse example:[^{]*(\{[^}]*[}|\W]+)""".r


  def main(args: Array[String]): Unit = {

    val apisToGen = Seq(
      "user" -> "submodules/kanboard_documentation/source/api/user_procedures.rst",
      "group" -> "submodules/kanboard_documentation/source/api/group_procedures.rst",
      "group.member" -> "submodules/kanboard_documentation/source/api/group_member_procedures.rst",
    ).map(t => t._1 -> t._2.toFile)

    val basePackageName = "model.kanboard.api.gen"
    val genFolder_init = "modules/lib/src/main/scala/".toFile / basePackageName.replace('.', '/')
    genFolder_init.delete(swallowIOExceptions = true)

    apisToGen.foreach { t => val (subPackage, docuFile) = t
      val docuContent = docuFile.contentAsString(StandardCharsets.UTF_8).stripMargin

      val packageName = basePackageName + "." + subPackage
      val methods: List[String] = """(\w*)(\r\n|\r|\n)-{3,}""".r.findAllMatchIn(docuContent).map(_.group(1)).toList
      val docuParts =  docuContent.split("""(\w*)(\r\n|\r|\n)-{3,}""").tail

      val r = methods.zip(docuParts).map(t => {
        val (method, content) = t

        val requestSplit = content.split("Request example:")

        val docu = requestSplit.head.trim

        val responseSplit = requestSplit.last.split("Response example:")

        val requestJson = "{" + responseSplit.head.split('{').tail.mkString(" { ").trim
        val responseJson = "{" + responseSplit.last.split('{').tail.mkString(" { ").trim

        KanboardDokuEntry(
          methodName = method,
          docu = docu,
          request = requestJson,
          response = responseJson,
          packageName = packageName
        )
      })

      val genFolder = genFolder_init / subPackage
      genFolder.createDirectoryIfNotExists()

      r.foreach(entry => {
        val genFile = genFolder / (entry.className + ".scala")

        println(genFile.pathAsString)

        genFile.writeText(entry.packageHeader)
        genFile.appendText(entry.imports)
        genFile.appendText("\n")
        genFile.appendText("/**\n")
        genFile.appendText("* GENERATED FILE - Any changes will be overwritten.\n")
        genFile.appendText("*/\n")
        genFile.appendText("\n")
        genFile.appendText(entry.docuComment)
        genFile.appendText("\n")
        genFile.appendText(entry.caseClassRequest)
        genFile.appendText("\n")
        genFile.appendText(entry.caseClassResponse)
        genFile.appendText("\n")
        genFile.appendText(entry.objectResponse)
      })
    }




  }
}
