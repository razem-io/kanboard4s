package model.kanboard.api

import model.kanboard.api.JsonRPCRequest.IsJsonRpcParamLike
import upickle.default.{macroRW, readwriter, ReadWriter => RW}

case class JsonRPCRequest(id: Long, jsonrpc: String, method: String, params: Map[String, IsJsonRpcParamLike]) {
  import upickle.default._
  def toJson: String = write(this)
}

object JsonRPCRequest {

  implicit val rw_IsJsonRpcParamLike: RW[IsJsonRpcParamLike] = readwriter[String].bimap[IsJsonRpcParamLike](
    {
      case StringParam(s) => s"$s"
      case IntParam(i) => i.toString
    },
    _ => throw new NotImplementedError()
  )

  implicit val rw: RW[JsonRPCRequest] = macroRW

  def apply(method: String, params: (String, IsJsonRpcParamLike)*): JsonRPCRequest = {
    JsonRPCRequest(id = System.currentTimeMillis, jsonrpc = "2.0", method = method, params = params.toMap)
  }

  case class StringParam(s: String) extends IsJsonRpcParamLike
  case class IntParam(i: Int) extends IsJsonRpcParamLike

  sealed trait IsJsonRpcParamLike
}
