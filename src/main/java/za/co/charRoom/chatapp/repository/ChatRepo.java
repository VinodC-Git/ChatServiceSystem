package za.co.charRoom.chatapp.repository;

import za.co.charRoom.chatapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<ChatMessage, Long> {

    @Query(value = "SELECT * FROM chat_message u WHERE u.chat_room_id = 111",nativeQuery = true)
    Optional<List<ChatMessage>> findByChatRoomId();
}
