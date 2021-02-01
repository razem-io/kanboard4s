package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get user information**
* -  Parameters:
* 
*    -  **username** (string, required)
* 
* -  Result on success: **user properties**
* -  Result on failure: **null**
**/

case class Kanboard_Request_getUserByName(username: String) extends KanboardApiCall[Kanboard_Response_getUserByName] {
  override val rpcMethodName: String = "getUserByName"

  override val rpcParameters: Seq[(String, String)] = Seq("username" -> username)
}

case class Kanboard_Response_getUserByName(name: String, email: String, role: String, username: String, notifications_enabled: String, is_ldap_user: String, id: String, google_id: Option[String], github_id: Option[String], password: String)
