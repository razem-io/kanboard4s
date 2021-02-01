package model.kanboard.api

import scala.concurrent.{ExecutionContext, Future}

trait KanboardApiCall [A] {

  val rpcMethodName: String

  val rpcParameters: Seq[(String, String)]

  def rpcRequest: JsonRPCRequest = JsonRPCRequest (
    rpcMethodName,
    rpcParameters: _ *
  )

  def parseRpcResponse(rawJson: String)(implicit jsonFormat: Format[A]): Option[A] = Json
    .parse(rawJson)
    .asOpt[A]

  def execute(implicit
              ec: ExecutionContext,
              jsonFormat: Format[A],
              executeRequest: JsonRPCRequest => Future[String]
             ): FutureO[A] = FutureO(
    executeRequest(rpcRequest).map(parseRpcResponse)
  )
}
