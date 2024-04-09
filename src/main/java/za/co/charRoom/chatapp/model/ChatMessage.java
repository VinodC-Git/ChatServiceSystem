package za.co.charRoom.chatapp.model;

import lombok.*;

import javax.persistence.*;
import java.nio.file.FileStore;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * Represents a chat message in the chat application.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatMessage {

    @Id
    private int id;
    private String content;
    private String sender;
    private MessageType type;
     Date timestamp;
    private String chatRoomId;


    public enum MessageType {
        CHAT, LEAVE, JOIN
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
