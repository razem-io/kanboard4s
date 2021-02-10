package model.kanboard.api.gen.group.member
import model.kanboard.api.KanboardApiCall
import model.kanboard.api.JsonRPCRequest._

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

case class Kanboard_Request_getMemberGroups(user_id: Int) extends KanboardApiCall[Kanboard_Response_getMemberGroups] {
  override val rpcMethodName: String = "getMemberGroups"

  override val rpcParameters: Seq[(String, IsJsonRpcParamLike)] = Seq("user_id" -> IntParam(user_id))
}

case class Kanboard_Response_getMemberGroups_Entries_Result(id: String, name: String)
object Kanboard_Response_getMemberGroups_Entries_Result {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getMemberGroups_Entries_Result] = macroRW
}
case class Kanboard_Response_getMemberGroups(result: Array[Kanboard_Response_getMemberGroups_Entries_Result])

object Kanboard_Response_getMemberGroups {
  import model.kanboard.api.Kanboard4sPickler.{ReadWriter => RW, macroRW}
  implicit val rw: RW[Kanboard_Response_getMemberGroups] = macroRW
}