package model.kanboard.api


import model.kanboard.api.JsonRPCRequest.IsJsonRpcParamLike
import model.kanboard.api.Kanboard4sPickler._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

trait KanboardApiCall [A] {

  val rpcMethodName: String

  val rpcParameters: Seq[(String, IsJsonRpcParamLike)]

  def rpcRequest: JsonRPCRequest = JsonRPCRequest (
    rpcMethodName,
    rpcParameters: _ *
  )

  def parseRpcResponse(rawJson: String)(implicit jsonReader: Reader[A]): Try[A] = Try {
    read[A](rawJson)
  }

  def execute(implicit
              ec: ExecutionContext,
              jsonFormat: Reader[A],
              executeRequest: JsonRPCRequest => Future[String]
             ): Future[Either[ResponseError, A]] =
    executeRequest(rpcRequest)
      .map(b => {
        parseRpcResponse(b).toEither.left.map(ResponseError(b, _))
      })
}

case class ResponseError(body: String, ex: Throwable)
