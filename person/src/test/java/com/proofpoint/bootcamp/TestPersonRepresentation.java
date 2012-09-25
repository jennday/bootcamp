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
package com.proofpoint.bootcamp;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.proofpoint.json.JsonCodec;
import org.testng.annotations.Test;

import java.net.URI;

import static com.proofpoint.json.JsonCodec.jsonCodec;
import static com.proofpoint.testing.EquivalenceTester.equivalenceTester;
import static org.testng.Assert.assertEquals;

public class TestPersonRepresentation
{
    private final JsonCodec<PersonRepresentation> codec = jsonCodec(PersonRepresentation.class);

    @Test
    public void testEquivalence()
            throws Exception
    {
        equivalenceTester()
                .addEquivalentGroup(new PersonRepresentation("foo", "foo@example.com", "Mr Foo", URI.create("http://localhost:8080/v1/person/foo")),
                        new PersonRepresentation("foo", "foo@example.com", "Mr Foo", URI.create("http://localhost:8080/v1/person/foo")))
                .addEquivalentGroup(new PersonRepresentation("bla", "foo@example.com", "Mr Foo", URI.create("http://localhost:8080/v1/person/foo")))
                .addEquivalentGroup(new PersonRepresentation("foo", "bla@example.com", "Mr Foo", URI.create("http://localhost:8080/v1/person/foo")))
                .addEquivalentGroup(new PersonRepresentation("foo", "foo@example.com", "Mr Bla", URI.create("http://localhost:8080/v1/person/foo")))
                .addEquivalentGroup(new PersonRepresentation("foo", "foo@example.com", "Mr Foo", URI.create("http://localhost:8080/v1/person/bla")))
                .check();
    }

    @Test
    public void testJsonRoundTrip()
    {
        PersonRepresentation expected = new PersonRepresentation("alice", "alice@example.com", "Alice", URI.create("http://localhost:8080/v1/person/alice"));
        String json = codec.toJson(expected);
        PersonRepresentation actual = codec.fromJson(json);
        assertEquals(actual, expected);
    }

    @Test
    public void testJsonDecode()
            throws Exception
    {
        PersonRepresentation expected = new PersonRepresentation("foo", "foo@example.com", "Mr Foo", null);

        String json = Resources.toString(Resources.getResource("single.json"), Charsets.UTF_8);
        PersonRepresentation actual = codec.fromJson(json);

        assertEquals(actual, expected);
    }
}
