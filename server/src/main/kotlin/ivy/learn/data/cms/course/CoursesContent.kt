package ivy.learn.data.cms.course

import ivy.learn.data.cms.course.algorithms.AlgorithmFoundations
import ivy.learn.data.cms.course.algorithms.DataStructures
import ivy.learn.data.cms.dsl.CoursesDsl

object CoursesContent : CoursesDsl({
    course(AlgorithmFoundations)
    course(DataStructures)
})