package kanboard.api

import model.kanboard.api.gen.group._
import model.kanboard.api.gen.group.member._
import model.kanboard.api.gen.user.Kanboard_Request_createUser

class GroupMemberApiTest extends KanboardForAllTest {

  private val expectedUser2Id = 2
  private val expectedUsername2 = "User2"

  private val expectedGroup1Id = 1
  private val expectedGroupName1 = "Group1"

  test("createUser") {
    for {
      responseTest1 <- Kanboard_Request_createUser(expectedUsername2, "verystrongpassword").execute
    } yield {
      assert(responseTest1.isRight)
      assert(responseTest1.exists(_.result == expectedUser2Id))
    }
  }

  test("createGroup") {
    for {
      responseTest1 <- Kanboard_Request_createGroup(expectedGroupName1).execute
    } yield {
      assert(responseTest1.isRight)

      assert(responseTest1.exists(_.result == expectedGroup1Id))
    }
  }

  test("addGroupMember") {
    for {
      response <- Kanboard_Request_addGroupMember(expectedGroup1Id, expectedUser2Id).execute
    } yield {
      assert(response.isRight)

      assert(response.exists(_.result))
    }
  }

  test("getGroupMembers") {
    for {
      response <- Kanboard_Request_getGroupMembers(expectedGroup1Id).execute
    } yield {
      assert(response.isRight)

      assert(response.exists(_.result.length == 1))
      assert(response.exists(_.result.head.user_id == expectedUser2Id.toString))
    }
  }

  test("getMemberGroups") {
    for {
      response <- Kanboard_Request_getMemberGroups(expectedUser2Id).execute
    } yield {
      assert(response.isRight)

      assert(response.exists(_.result.length == 1))
      assert(response.exists(_.result.head.id == expectedGroup1Id.toString))
    }
  }

  test("isGroupMember") {
    for {
      response <- Kanboard_Request_isGroupMember(expectedGroup1Id, expectedUser2Id).execute
    } yield {
      assert(response.isRight)

      assert(response.exists(_.result))
    }
  }

  test("removeGroupMember") {
    for {
      responseBefore <- Kanboard_Request_isGroupMember(expectedGroup1Id, expectedUser2Id).execute
      response <- Kanboard_Request_removeGroupMember(expectedGroup1Id, expectedUser2Id).execute
      responseAfter <- Kanboard_Request_isGroupMember(expectedGroup1Id, expectedUser2Id).execute
    } yield {
      assert(responseBefore.isRight)
      assert(response.isRight)
      assert(responseAfter.isRight)

      assert(responseBefore.exists(_.result))
      assert(response.exists(_.result))
      assert(!responseAfter.exists(_.result))
    }
  }
}
