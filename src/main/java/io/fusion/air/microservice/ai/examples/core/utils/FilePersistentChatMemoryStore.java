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
package io.fusion.air.microservice.ai.examples.core.utils;
// LangChain4J
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

import static dev.langchain4j.data.message.ChatMessageDeserializer.messagesFromJson;
import static dev.langchain4j.data.message.ChatMessageSerializer.messagesToJson;

// MapDB
// import org.mapdb.DB;
// import org.mapdb.DBMaker;
// import static org.mapdb.Serializer.INTEGER;
// import static org.mapdb.Serializer.STRING;
import org.springframework.stereotype.Component;

// Java
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File based Persistent Store for Chat Memory.
 * MapDB is used for storing the data into a file.
 *
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Component
public class FilePersistentChatMemoryStore implements ChatMemoryStore {
    // mapdb dependency has some issues.
    // private final DB db = DBMaker.fileDB("multi-user-chat-memory.db").transactionEnable().make();
    // private final Map<Integer, String> map = db.hashMap("messages", STRING, STRING).createOrOpen();
    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String json = map.get((String) memoryId);
        return messagesFromJson(json);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        String json = messagesToJson(messages);
        map.put((String) memoryId, json);
        // db.commit();
    }

    @Override
    public void deleteMessages(Object memoryId) {
        map.remove((String) memoryId);
        // db.commit();
    }
}
