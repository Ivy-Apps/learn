package ivy.learn.data.cms

import ivy.learn.data.cms.course.ProgrammingFundamentals
import ivy.model.Topic
import ivy.model.TopicId

object Topics {
    val GeneralProgramming = Topic(
        id = TopicId("general-programming"),
        name = "General Programming",
        courses = listOf(
            ProgrammingFundamentals.course.id
        )
    )

    val list = listOf(
        GeneralProgramming
    )
}