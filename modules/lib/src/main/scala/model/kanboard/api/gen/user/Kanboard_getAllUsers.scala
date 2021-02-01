package model.kanboard.api.gen.user
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get all available users**
* -  Parameters:
* 
*    -  **none**
* 
* -  Result on success: **List of users**
* -  Result on failure: **false**
**/

case class Kanboard_Request_getAllUsers() extends KanboardApiCall[Kanboard_Response_getAllUsers] {
  override val rpcMethodName: String = "getAllUsers"

  override val rpcParameters: Seq[(String, String)] = Seq()
}

case class Kanboard_Response_getAllUsers_Entries(name: String, email: String, role: String, username: String, notifications_enabled: String, is_ldap_user: String, id: String, google_id: Option[String], github_id: Option[String])
object Kanboard_Response_getAllUsers_Entries {
  implicit val jsonFormat:Format[Kanboard_Response_getAllUsers_Entries] = Json.format[Kanboard_Response_getAllUsers_Entries]
}
case class Kanboard_Response_getAllUsers(result: Array[Kanboard_Response_getAllUsers_Entries])