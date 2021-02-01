package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get all groups for a given user**
* -  Parameters:
* 
*    -  **user_id** (integer, required)
* 
* -  Result on success: **List of groups**
* -  Result on failure: **false**
**/

case class Kanboard_Request_getMemberGroups() extends KanboardApiCall[Kanboard_Response_getMemberGroups] {
  override val rpcMethodName: String = "getMemberGroups"

  override val rpcParameters: Seq[(String, String)] = Seq()
}

case class Kanboard_Response_getMemberGroups_Entries(id: String, name: String)
object Kanboard_Response_getMemberGroups_Entries {
  implicit val jsonFormat:Format[Kanboard_Response_getMemberGroups_Entries] = Json.format[Kanboard_Response_getMemberGroups_Entries]
}
case class Kanboard_Response_getMemberGroups(result: Array[Kanboard_Response_getMemberGroups_Entries])
