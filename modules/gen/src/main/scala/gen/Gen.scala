package io.razem.kanboard4s
package gen

object Gen {

  val projectProceduresExceptions: Seq[Exception] = Seq(
    Exception(
      "getProjectActivity",
      responseParameters = Seq("result: List[String]")
    ),
    Exception(
      "getProjectActivities",
      responseParameters = Seq("result: List[String]")
    )
  )
}

case class Exception(methodName: String, requestParameters: Seq[String] = Nil, responseParameters: Seq[String] = Nil,  imports: Seq[String] = Nil)