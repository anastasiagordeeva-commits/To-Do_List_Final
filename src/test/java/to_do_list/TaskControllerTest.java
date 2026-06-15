package to_do_list;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createTask_shouldReturn201() throws Exception {
        String json = """
        {
          "title": "Task A",
          "description": "Desc",
          "status": "DONE",
          "deadline": "2030-01-01"
        }
        """;
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Task A"));
    }

    @Test
    void getTaskById_shouldReturn200() throws Exception {
        String json = """
        {
          "title": "Task B",
          "description": "Desc",
          "status": "DONE",
          "deadline": "2030-01-01"
        }
        """;
        String response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode node = objectMapper.readTree(response);
        long id = node.get("id").asLong();
        mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void updateTask_shouldReturn200() throws Exception {
        String createJson = """
        {
          "title": "Old",
          "description": "Old desc",
          "status": "DONE",
          "deadline": "2030-01-01"
        }
        """;
        String response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andReturn()
                .getResponse()
                .getContentAsString();
        JsonNode node = objectMapper.readTree(response);
        long id = node.get("id").asLong();

        String updateJson = """
        {
          "title": "Updated",
          "description": "Updated desc",
          "status": "DONE",
          "deadline": "2031-01-01"
        }
        """;
        mockMvc.perform(put("/tasks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"));
    }

    @Test
    void deleteTask_shouldReturn204() throws Exception {
        String json = """
        {
          "title": "To delete",
          "description": "Desc",
          "status": "DONE",
          "deadline": "2030-01-01"
        }
        """;
        String response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode node = objectMapper.readTree(response);
        long id = node.get("id").asLong();
        mockMvc.perform(delete("/tasks/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void createTask_shouldReturn400_whenTitleEmpty() throws Exception {
        String json = """
        {
          "title": "",
          "description": "Desc",
          "status": "DONE",
          "deadline": "2030-01-01"
        }
        """;
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getTask_shouldReturn404_whenNotExists() throws Exception {
        mockMvc.perform(get("/tasks/999999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createTask_shouldReturn400_whenInvalidStatus() throws Exception {
        String json = """
        {
          "title": "Task",
          "description": "Desc",
          "status": "WRONG_STATUS",
          "deadline": "2030-01-01"
        }
        """;
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}