package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

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

case class Kanboard_Request_isActiveUser(user_id: String) extends KanboardApiCall[Kanboard_Response_isActiveUser] {
  override val rpcMethodName: String = "isActiveUser"

  override val rpcParameters: Seq[(String, String)] = Seq("user_id" -> user_id)
}

case class Kanboard_Response_isActiveUser(result: Boolean)

object Kanboard_Response_isActiveUser {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_isActiveUser] = macroRW
}