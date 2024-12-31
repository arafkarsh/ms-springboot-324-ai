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
package io.fusion.air.microservice.adapters.service;

import io.fusion.air.microservice.domain.entities.example.ChatMessageEntity;
import io.fusion.air.microservice.adapters.repository.ChatMessageRepository;
import io.fusion.air.microservice.domain.ports.services.ChatMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Araf Karsh Hamid
 * @version:
 * @date:
 */
@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    // Autowired using Constructor Injection
    private ChatMessageRepository chatMessageRepository;

    /**
     * Autowired using Constructor Injection
     * @param chatMessageRepository
     */
    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    /**
     * Returns List of Chat Messages from ChatMessages
     *
     * @param userId
     * @return
     */
    @Override
    public List<ChatMessageEntity> fetchByUserId(String userId) {
        return chatMessageRepository.fetchByUserId(userId);
    }
}
