package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get user information**
* -  Parameters:
* 
*    -  **user_id** (integer, required)
* 
* -  Result on success: **user properties**
* -  Result on failure: **null**
**/

case class Kanboard_Request_getUser(user_id: String) extends KanboardApiCall[Kanboard_Response_getUser] {
  override val rpcMethodName: String = "getUser"

  override val rpcParameters: Seq[(String, String)] = Seq("user_id" -> user_id)
}

case class Kanboard_Response_getUser(name: String, email: String, role: String, username: String, notifications_enabled: String, is_ldap_user: String, id: String, google_id: Option[String], github_id: Option[String], password: String)
