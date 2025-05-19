package dev.samir.backangulart.controller;

import dev.samir.backangulart.api.controller.ClothController;
import dev.samir.backangulart.api.dto.ClothDto;
import dev.samir.backangulart.api.dto.mapper.ClothDtoMapper;
import dev.samir.backangulart.api.exception.GlobalExceptionHandler;
import dev.samir.backangulart.api.exception.ResourceNotFoundException;
import dev.samir.backangulart.application.Service.ClothService;
import dev.samir.backangulart.domain.EnumCloth;
import dev.samir.backangulart.domain.model.Cloth;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClothController.class)
@Import({ClothControllerTest.TestConfig.class, GlobalExceptionHandler.class})
public class ClothControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClothService clothService;

    @Autowired
    private ClothDtoMapper clothDtoMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ClothService clothService() {
            return Mockito.mock(ClothService.class);
        }

        @Bean
        public ClothDtoMapper clothDtoMapper() {
            return Mockito.mock(ClothDtoMapper.class);
        }
    }

    @Test
    void shouldCreateCloth() throws Exception {
        ClothDto request = new ClothDto("Shirt", EnumCloth.sizeM, "Blue");
        Cloth cloth = new Cloth("Shirt", EnumCloth.sizeM, "Blue");

        when(clothDtoMapper.toDomain(Mockito.any(ClothDto.class))).thenReturn(cloth);
        when(clothService.create(Mockito.any(Cloth.class))).thenReturn(cloth);
        when(clothDtoMapper.toDto(Mockito.any(Cloth.class))).thenReturn(request);

        mockMvc.perform(post("/clothes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                      {
                        "name": "Shirt",
                        "color": "Blue",
                        "size": "sizeM"
                      }

                      """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Shirt")))
                .andExpect(jsonPath("$.color", is("Blue")))
                .andExpect(jsonPath("$.size", is("sizeM")));
    }

    @Test
    void shouldListAllClothes() throws Exception {
        List<Cloth> clothes = List.of(
                new Cloth("Shirt", EnumCloth.sizeM, "Blue"),
                new Cloth("Jacket", EnumCloth.sizeM, "Black")
        );

        when(clothService.listAll()).thenReturn(clothes);

        mockMvc.perform(get("/clothes/show")
                            .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.size()").value(clothes.size()))
                .andExpect(jsonPath("$[0].name", is("Shirt")))
                .andExpect(jsonPath("$[0].color", is("Blue")))
                .andExpect(jsonPath("$[0].size", is("sizeM")))
                .andExpect(jsonPath("$[1].name", is("Jacket")))
                .andExpect(jsonPath("$[1].color", is("Black")))
                .andExpect(jsonPath("$[1].size", is("sizeM")));

        verify(clothService, times(1)).listAll();

    }

    @Test
    void shouldFindClothById() throws Exception {
        Long id = 1L;
        Cloth cloth = new Cloth("T-shirt", EnumCloth.sizeM, "Blue");

        when(clothService.findById(id)).thenReturn(cloth);
        when(clothDtoMapper.toDto(cloth))
                .thenReturn(new ClothDto("T-shirt", EnumCloth.sizeM, "Blue"));

        mockMvc.perform(get("/clothes/show/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("T-shirt")))
                .andExpect(jsonPath("$.color", is("Blue")))
                .andExpect(jsonPath("$.size", is("sizeM")));
    }

    @Test
    void shouldReturn404WhenClothNotFound() throws Exception {
        Long id = 999L;

        when(clothService.findById(id)).thenThrow(new ResourceNotFoundException("Cloth not found with id: " + id));

        mockMvc.perform(get("/clothes/show/{id}", id))
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldDeleteClothById() throws Exception {

        Long id = 1L;

        doNothing().when(clothService).delete(id);


        mockMvc.perform(delete("/clothes/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isNoContent());

        verify(clothService, times(1)).delete(id);
    }

    @Test
    void shouldUpdateCloth() throws Exception {

        Long id = 1L;

        ClothDto request = new ClothDto("Updated Shirt", EnumCloth.sizeM, "Blue");
        Cloth updatedCloth = new Cloth("Updated Shirt", EnumCloth.sizeM, "Blue");


        when(clothDtoMapper.toDomain(any(ClothDto.class))).thenReturn(updatedCloth);
        when(clothService.update(eq(id), any(Cloth.class))).thenReturn(Optional.of(updatedCloth));
        when(clothDtoMapper.toDto(any(Cloth.class))).thenReturn(request);

        mockMvc.perform(put("/clothes/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Updated Shirt",
                                "color": "Blue",
                                "size": "sizeM"
                            }
                            """))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value("Updated Shirt"))
                .andExpect(jsonPath("$.color").value("Blue"))
                .andExpect(jsonPath("$.size").value("sizeM"));

        verify(clothService, times(1)).update(eq(id), any(Cloth.class));
    }

    @Test
    void shouldReturnClothesBySize() throws Exception {

        String size = "sizeM";

        List<Cloth> clothes = List.of(
                new Cloth("Shirt", EnumCloth.sizeM, "Blue"),
                new Cloth("Polo", EnumCloth.sizeM, "White")
        );

        when(clothService.findBySize(EnumCloth.valueOf(size))).thenReturn(clothes);

        when(clothDtoMapper.toDto(any(Cloth.class)))
                .thenAnswer(invocation -> {
                    Cloth cloth = invocation.getArgument(0);
                    return new ClothDto(cloth.getName(), cloth.getSize(), cloth.getColor());
                });

        mockMvc.perform(get("/clothes/size/{size}", size)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.size()").value(clothes.size()))
                .andExpect(jsonPath("$[0].name").value("Shirt"))
                .andExpect(jsonPath("$[0].color").value("Blue"))
                .andExpect(jsonPath("$[0].size").value("sizeM"))
                .andExpect(jsonPath("$[1].name").value("Polo"))
                .andExpect(jsonPath("$[1].color").value("White"))
                .andExpect(jsonPath("$[1].size").value("sizeM"));

        verify(clothService, times(1)).findBySize(EnumCloth.valueOf(size));
    }

}
