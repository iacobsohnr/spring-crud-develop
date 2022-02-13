package com.demo.springcrud.controllers;

import com.demo.springcrud.model.Student;
import com.demo.springcrud.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(StudentController.class)

public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void shouldReturnEmptyListWhenGettingWithNoElementsRetured() throws Exception{
        mockMvc.perform(get("/students"))
                .andExpect(content().string("[]"));
    }
    @Test
    public void shouldReturnObjectWhenFoundById() throws Exception {
        when(studentRepository.findById(any())).thenReturn(Optional.of(new Student(1L,"mint",3)));
            mockMvc.perform(get("/student/1"))
                    .andExpect(content().string("{\"id\":1,\"name\":\"mint\",\"grade\":3}"));
    }
}
