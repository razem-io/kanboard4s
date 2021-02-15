package io.razem.kanboard4s
package gen

import better.files._

import java.nio.charset.StandardCharsets
import scala.util.matching.Regex


object GenClasses {

  //TODO
  //tags_procedures.rst: Error in generated class Kanboard_getTaskTags.scala: "1: String, 2: String" as param
  //project_procedures.rst: Fails because of nested "url" array in response example
  //project_permission_procedures.rst: Error in generated class Kanboard_getProjectUsers.scala: "1: String" as param
  //task_procedures.rst: Fails because of nested "color" array in response example

  def main(args: Array[String]): Unit = {

    val apisToGen = Seq(
      "user" -> "submodules/kanboard_documentation/source/api/user_procedures.rst",
      "group" -> "submodules/kanboard_documentation/source/api/group_procedures.rst",
      "group.member" -> "submodules/kanboard_documentation/source/api/group_member_procedures.rst",
      //"tags" -> "submodules/kanboard_documentation/source/api/tags_procedures.rst",
      //"project" -> "submodules/kanboard_documentation/source/api/project_procedures.rst",
      //"project.permission" -> "submodules/kanboard_documentation/source/api/project_permission_procedures.rst",
      "column" -> "submodules/kanboard_documentation/source/api/column_procedures.rst",
      //"task" -> "submodules/kanboard_documentation/source/api/task_procedures.rst",
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
