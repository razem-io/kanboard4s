package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Disable a user**
* -  Parameters:
* 
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_disableUser(user_id: Int) extends KanboardApiCall[Kanboard_Response_disableUser] {
  override val rpcMethodName: String = "disableUser"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("user_id" -> IntParam(user_id))
}

case class Kanboard_Response_disableUser(result: Boolean)

object Kanboard_Response_disableUser {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_disableUser] = macroRW
}