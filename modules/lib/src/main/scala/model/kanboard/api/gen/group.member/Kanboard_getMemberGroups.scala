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

case class Kanboard_Response_getMemberGroups_Entries_Result(id: String, name: String)
object Kanboard_Response_getMemberGroups_Entries_Result {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getMemberGroups_Entries_Result] = macroRW
}
case class Kanboard_Response_getMemberGroups(result: Array[Kanboard_Response_getMemberGroups_Entries_Result])

object Kanboard_Response_getMemberGroups {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getMemberGroups] = macroRW
}