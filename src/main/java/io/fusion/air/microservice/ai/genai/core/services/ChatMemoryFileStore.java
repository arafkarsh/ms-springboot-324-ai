/**
 * (C) Copyright 2024 Araf Karsh Hamid
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
package io.fusion.air.microservice.ai.genai.core.services;
// LangChain4J
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import static dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson;
import static dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson;
// MapDB
import org.mapdb.DB;
import org.mapdb.DBMaker;
import static org.mapdb.Serializer.STRING;
import org.mapdb.HTreeMap;
// Spring
import org.springframework.stereotype.Component;
// Java
import java.util.List;

/**
 * File based Persistent Store for Chat Memory.
 * MapDB is used for storing the data into a file.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Component
public class ChatMemoryFileStore implements ChatMemoryStore {
    // MapDB is a persistent store
    private final DB db;
    private final HTreeMap<String, String> map;
    // private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    /**
     * Create the Persistent Map
     */
    public ChatMemoryFileStore() {
        db = DBMaker.fileDB("multi-user-chat-memory.db").transactionEnable().make();
        map = db.hashMap("messages", STRING, STRING).createOrOpen();
    }
    /**
     * Get Messages
     * @param _memoryId
     * @return
     */
    @Override
    public List<ChatMessage> getMessages(Object _memoryId) {
        String json = map.get((String) _memoryId);
        return messagesFromJson(json);
    }

    /**
     * Update Message based on Memory ID
     * @param _memoryId
     * @param _messages
     */
    @Override
    public void updateMessages(Object _memoryId, List<ChatMessage> _messages) {
        String json = messagesToJson(_messages);
        map.put((String) _memoryId, json);
        db.commit();
    }

    /**
     * Delete Message based on Memory ID
     * @param memoryId
     */
    @Override
    public void deleteMessages(Object memoryId) {
        map.remove((String) memoryId);
        db.commit();
    }

    public static void main(String[] args) {
        ChatMemoryFileStore store = new ChatMemoryFileStore();
    }
}
