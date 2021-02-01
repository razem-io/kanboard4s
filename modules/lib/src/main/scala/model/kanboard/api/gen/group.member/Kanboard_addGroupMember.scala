package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Add a user to a group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
*    -  **user_id** (integer, required)
* 
* -  Result on success: **true**
* -  Result on failure: **false**
**/

case class Kanboard_Request_addGroupMember() extends KanboardApiCall[Kanboard_Response_addGroupMember] {
  override val rpcMethodName: String = "addGroupMember"

  override val rpcParameters: Seq[(String, String)] = Seq()
}

case class Kanboard_Response_addGroupMember(result: Boolean)
