package model.kanboard.api.gen.group
import model.kanboard.api.KanboardApiCall

/**
* GENERATED FILE - Any changes will be overwritten.
*/

/**
* -  Purpose: **Get all groups**
* -  Parameters: none
* -  Result on success: **list of groups**
* -  Result on failure: **false**
**/

case class Kanboard_Request_getAllGroups() extends KanboardApiCall[Kanboard_Response_getAllGroups] {
  override val rpcMethodName: String = "getAllGroups"

  override val rpcParameters: Seq[(String, String)] = Seq()
}

case class Kanboard_Response_getAllGroups_Entries_Result(id: String, external_id: String, name: String)
object Kanboard_Response_getAllGroups_Entries_Result {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getAllGroups_Entries_Result] = macroRW
}
case class Kanboard_Response_getAllGroups(result: Array[Kanboard_Response_getAllGroups_Entries_Result])

object Kanboard_Response_getAllGroups {
  import upickle.default.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getAllGroups] = macroRW
}