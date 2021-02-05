package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Enable a user**
* -  Parameters:
* 
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_enableUser(user_id: String) extends KanboardApiCall[Kanboard_Response_enableUser] {
  override val rpcMethodName: String = "enableUser"

  override val rpcParameters: Seq[(String, String)] = Seq("user_id" -> user_id)
}

case class Kanboard_Response_enableUser(result: Boolean)

object Kanboard_Response_enableUser {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_enableUser] = macroRW
}