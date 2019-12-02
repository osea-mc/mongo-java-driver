/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mongodb.scala.syncadapter

import java.util.concurrent.TimeUnit

import com.mongodb.client.AggregateIterable
import com.mongodb.client.model.Collation
import org.bson.conversions.Bson
import org.mongodb.scala.AggregateObservable

import scala.concurrent.duration.Duration

case class SyncAggregateIterable[T](wrapped: AggregateObservable[T])
    extends SyncMongoIterable[T]
    with AggregateIterable[T] {
  override def toCollection(): Unit = wrapped.toCollection().toFuture().get()

  override def allowDiskUse(allowDiskUse: java.lang.Boolean): AggregateIterable[T] = {
    wrapped.allowDiskUse(allowDiskUse)
    this
  }

  override def batchSize(batchSize: Int): AggregateIterable[T] = {
    wrapped.batchSize(batchSize)
    this
  }

  override def maxTime(maxTime: Long, timeUnit: TimeUnit): AggregateIterable[T] = {
    wrapped.maxTime(maxTime, timeUnit)
    this
  }

  override def maxAwaitTime(maxAwaitTime: Long, timeUnit: TimeUnit): AggregateIterable[T] = {
    wrapped.maxAwaitTime(Duration(maxAwaitTime, timeUnit))
    this
  }

  override def bypassDocumentValidation(bypassDocumentValidation: java.lang.Boolean): AggregateIterable[T] = {
    wrapped.bypassDocumentValidation(bypassDocumentValidation)
    this
  }

  override def collation(collation: Collation): AggregateIterable[T] = {
    wrapped.collation(collation)
    this
  }

  override def comment(comment: String): AggregateIterable[T] = {
    wrapped.comment(comment)
    this
  }

  override def hint(hint: Bson): AggregateIterable[T] = {
    wrapped.hint(hint)
    this
  }
}