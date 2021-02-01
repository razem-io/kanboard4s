package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Check if a user is member of a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_isGroupMember() extends KanboardApiCall[Kanboard_Response_isGroupMember] {
  override val rpcMethodName: String = "isGroupMember"

  override val rpcParameters: Seq[(String, String)] = Seq()
}

case class Kanboard_Response_isGroupMember(result: Boolean)
