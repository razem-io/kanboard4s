package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

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

case class Kanboard_Request_disableUser(user_id: String) extends KanboardApiCall[Kanboard_Response_disableUser] {
  override val rpcMethodName: String = "disableUser"

  override val rpcParameters: Seq[(String, String)] = Seq("user_id" -> user_id)
}

case class Kanboard_Response_disableUser(result: Boolean)

object Kanboard_Response_disableUser {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_disableUser] = macroRW
}