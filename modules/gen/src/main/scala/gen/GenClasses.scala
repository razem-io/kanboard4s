package io.razem.kanboard4s
package gen

import better.files._

import java.nio.charset.StandardCharsets

case class ApiEntry(subPackage: String, docuFile: File, exceptions: Seq[Exception]= Nil)

object GenClasses {

  def main(args: Array[String]): Unit = {

    val baseApiPath = "submodules/kanboard_documentation/source/api".toFile
    val apisToGen: Seq[ApiEntry] = Seq(
      ApiEntry("user", baseApiPath / "user_procedures.rst"),
      ApiEntry("group", baseApiPath / "group_procedures.rst"),
      ApiEntry("group.member", baseApiPath / "group_member_procedures.rst"),
      ApiEntry("tags", baseApiPath / "tags_procedures.rst"),
      ApiEntry("project", baseApiPath / "project_procedures.rst", Gen.projectProceduresExceptions),
      ApiEntry("project.permission", baseApiPath / "project_permission_procedures.rst"),
      ApiEntry("column", baseApiPath / "column_procedures.rst"),
      ApiEntry("task", baseApiPath / "task_procedures.rst"),
    )

    val basePackageName = "model.kanboard.api.gen"
    val genFolder_init = "modules/lib/src/main/scala/".toFile / basePackageName.replace('.', '/')
    genFolder_init.delete(swallowIOExceptions = true)

    apisToGen.foreach { apiEntry =>
      val subPackage = apiEntry.subPackage
      val docuContent = apiEntry.docuFile.contentAsString(StandardCharsets.UTF_8).stripMargin

      val packageName = basePackageName + "." + subPackage
      val methods: List[String] = """(\w*)(\r\n|\r|\n)-{3,}""".r.findAllMatchIn(docuContent).map(_.group(1)).toList
      val docuParts =  docuContent.split("""(\w*)(\r\n|\r|\n)-{3,}""").tail

      val r = methods.zip(docuParts).map(t => {
        val (methodName, content) = t

        val responseSuccess = content.split("Result on success:").last.split('\n').head.replace("*", "").trim
        val responseFailure = content.split("Result on failure:").last.split('\n').head.replace("*", "").trim

        val requestSplit = content.split("Request example:")

        val docu = requestSplit.head.trim

        val responseSplit = requestSplit.last.split("Response example:")

        val requestJson = "{" + responseSplit.head.split('{').tail.mkString(" { ").trim
        val responseJson = "{" + responseSplit.last.split('{').tail.mkString(" { ").trim

        KanboardDokuEntry(
          methodName = methodName,
          docu = docu,
          request = requestJson,
          response = responseJson,
          responseSuccess = responseSuccess,
          responseFailure = responseFailure,
          packageName = packageName,
          maybeException = apiEntry.exceptions.find(_.methodName == methodName)
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
