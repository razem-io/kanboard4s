package model.kanboard.api.gen.group
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get one group**
* -  Parameters:
* 
*    -  **group_id** (integer, required)
* 
* -  Result on success: **Group dictionary**
* -  Result on failure: **false**
**/

case class Kanboard_Request_getGroup() extends KanboardApiCall[Kanboard_Response_getGroup] {
  override val rpcMethodName: String = "getGroup"

  override val rpcParameters: Seq[(String, String)] = Seq()
}

case class Kanboard_Response_getGroup(id: String, external_id: String, name: String)
