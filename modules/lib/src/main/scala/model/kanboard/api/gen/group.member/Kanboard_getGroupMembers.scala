package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get all members of a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
* 
* -  Result on success: **List of users**
* -  Result on failure: **false**
**/

case class Kanboard_Request_getGroupMembers() extends KanboardApiCall[Kanboard_Response_getGroupMembers] {
  override val rpcMethodName: String = "getGroupMembers"

  override val rpcParameters: Seq[(String, String)] = Seq()
}

case class Kanboard_Response_getGroupMembers_Entries(group_id: String, name: Option[String], notifications_filter: String, email: Option[String], role: String, username: String, timezone: Option[String], notifications_enabled: String, user_id: String, is_project_admin: String, is_ldap_user: String, id: String, language: Option[String], disable_login_form: String, lock_expiration_date: String, gitlab_id: Option[String], nb_failed_login: String)
object Kanboard_Response_getGroupMembers_Entries {
  implicit val jsonFormat:Format[Kanboard_Response_getGroupMembers_Entries] = Json.format[Kanboard_Response_getGroupMembers_Entries]
}
case class Kanboard_Response_getGroupMembers(result: Array[Kanboard_Response_getGroupMembers_Entries])
