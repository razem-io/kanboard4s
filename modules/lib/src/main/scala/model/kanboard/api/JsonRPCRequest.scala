package model.kanboard.api

import model.kanboard.api.JsonRPCRequest.IsJsonRpcParamLike
import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}

case class JsonRPCRequest(id: Long, jsonrpc: String, method: String, params: Map[String, IsJsonRpcParamLike]) {
  import model.kanboard.api.Kanboard4sPickler._
  def toJson: String = write(this)
}

object JsonRPCRequest {

  implicit val rw_IsJsonRpcParamLike: RW[IsJsonRpcParamLike] = model.kanboard.api.Kanboard4sPickler.readwriter[String].bimap[IsJsonRpcParamLike](
    {
      case StringParam(s) => s"$s"
      case IntParam(i) => i.toString
      case ArrayStringParam(i) => i.mkString("[", ",", "]")
      case BooleanParam(b) => b.toString
    },
    _ => throw new NotImplementedError()
  )

  implicit val rw: RW[JsonRPCRequest] = macroRW

  def apply(method: String, params: (String, IsJsonRpcParamLike)*): JsonRPCRequest = {
    JsonRPCRequest(id = System.currentTimeMillis, jsonrpc = "2.0", method = method, params = params.toMap)
  }

  case class StringParam(s: String) extends IsJsonRpcParamLike
  case class IntParam(i: Int) extends IsJsonRpcParamLike
  case class ArrayStringParam(aS: Array[String]) extends IsJsonRpcParamLike
  case class ArrayIntParam(aS: Array[Int]) extends IsJsonRpcParamLike
  case class BooleanParam(b: Boolean) extends IsJsonRpcParamLike

  sealed trait IsJsonRpcParamLike
}
