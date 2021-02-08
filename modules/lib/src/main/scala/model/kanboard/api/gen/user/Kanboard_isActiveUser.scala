package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Check if a user is active**
* -  Parameters:
* 
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_isActiveUser(user_id: Int) extends KanboardApiCall[Kanboard_Response_isActiveUser] {
  override val rpcMethodName: String = "isActiveUser"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("user_id" -> IntParam(user_id))
}

case class Kanboard_Response_isActiveUser(result: Boolean)

object Kanboard_Response_isActiveUser {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_isActiveUser] = macroRW
}