/*
 * Copyright 2010 Proofpoint, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.proofpoint.bootcamp.monitor;

import org.weakref.jmx.Managed;

import java.util.concurrent.atomic.AtomicLong;

public class PersonStoreStats
{
    private final AtomicLong fetched = new AtomicLong();
    private final AtomicLong added = new AtomicLong();
    private final AtomicLong updated = new AtomicLong();
    private final AtomicLong deleted = new AtomicLong();
    private final AtomicLong allFetched = new AtomicLong();

    @Managed
    public long getFetched()
    {
        return fetched.get();
    }

    @Managed
    public long getAdded()
    {
        return added.get();
    }

    @Managed
    public long getUpdated()
    {
        return updated.get();
    }

    @Managed
    public long getDeleted()
    {
        return deleted.get();
    }

    @Managed
    public long getAllFetched()
    {
        return allFetched.get();
    }

    public void personFetched()
    {
        fetched.getAndIncrement();
    }

    public void personAdded()
    {
        added.getAndIncrement();
    }

    public void personUpdated()
    {
        updated.getAndIncrement();
    }

    public void personDeleted()
    {
        deleted.getAndIncrement();
    }

    public void allPersonsFetched()
    {
        allFetched.getAndIncrement();
    }
}
