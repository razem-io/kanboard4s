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

  def executeF(implicit
              ec: ExecutionContext,
              jsonFormat: Reader[A],
              executeRequest: JsonRPCRequest => Future[String]
             ): FutureResponse[A] =
    FutureResponse(execute)
}

case class ResponseError(body: String, ex: Throwable)

case class FutureResponse[+A](future: Future[Either[ResponseError, A]]) extends AnyVal {
  def flatMap[B](f: A => FutureResponse[B])(implicit ec: ExecutionContext): FutureResponse[B] = {
    val newFuture = future.flatMap{
      case Right(a) => f(a).future
      case Left(e) => Future.successful(Left(e))
    }
    FutureResponse(newFuture)
  }

  def map[B](f: A => B)(implicit ec: ExecutionContext): FutureResponse[B] = {
    FutureResponse(future.map(option => option map f))
  }
}

object FutureResponse {
  def successful[A](a: A): FutureResponse[A] = FutureResponse(Future.successful(Right(a)))
}
