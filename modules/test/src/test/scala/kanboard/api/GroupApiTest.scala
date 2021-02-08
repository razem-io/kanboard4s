package kanboard.api

import model.kanboard.api.gen.group._

class GroupApiTest extends KanboardForAllTest {

  private val expectedGroup1Id = 1
  private val expectedGroup2Id = 2

  private val expectedUsername1 = "Group1"
  private val expectedUsername2 = "Group2"

  test("createGroup") {
    for {
      responseTest1 <- Kanboard_Request_createGroup(expectedUsername1).execute
      responseTest2 <- Kanboard_Request_createGroup(expectedUsername2).execute
    } yield {
      assert(responseTest1.isRight)
      assert(responseTest2.isRight)

      assert(responseTest1.exists(_.result == expectedGroup1Id))
      assert(responseTest2.exists(_.result == expectedGroup2Id))
    }
  }

  test("getGroup") {
    for {
      response <- Kanboard_Request_getGroup(expectedGroup1Id).execute
    } yield {
      assert(response.isRight)

      assert(response.exists(_.result.name == expectedUsername1))
    }
  }

  test("getAllGroups") {
    for {
      response <- Kanboard_Request_getAllGroups().execute
    } yield {
      assert(response.isRight)

      assert(response.exists(_.result.length == 2))
    }
  }

  test("removeGroup") {
    for {
      responseBefore <- Kanboard_Request_getGroup(expectedGroup1Id).execute
      response <- Kanboard_Request_removeGroup(expectedGroup1Id).execute
      responseAfter <- Kanboard_Request_getGroup(expectedGroup1Id).execute
    } yield {
      assert(responseBefore.isRight)
      assert(response.isRight)
      assert(responseAfter.isRight)

      assert(responseBefore.exists(_.result.name == expectedUsername1))
      assert(response.exists(_.result))
      assert(responseAfter.exists(_.result == null))
    }
  }

  test("updateGroup") {
    for {
      responseBefore <- Kanboard_Request_getGroup(expectedGroup2Id).execute
      response <- Kanboard_Request_updateGroup(expectedGroup2Id, name = Some("Updated Name")).execute
      responseAfter <- Kanboard_Request_getGroup(expectedGroup2Id).execute
    } yield {
      assert(responseBefore.isRight)
      assert(response.isRight)
      assert(responseAfter.isRight)

      assert(responseBefore.exists(_.result.name == expectedUsername2))
      assert(response.exists(_.result))
      assert(responseAfter.exists(_.result.name == "Updated Name"))
    }
  }
}
