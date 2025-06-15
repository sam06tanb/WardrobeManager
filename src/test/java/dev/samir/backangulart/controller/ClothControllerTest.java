package dev.samir.backangulart.controller;

import dev.samir.backangulart.api.controller.ClothController;
import dev.samir.backangulart.api.dto.ClothResponseDto;
import dev.samir.backangulart.api.dto.CreateClothRequestDto;
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
        CreateClothRequestDto request = new CreateClothRequestDto(1L,"Shirt", EnumCloth.sizeM, "Blue");
        Cloth cloth = new Cloth(1L, "Shirt", EnumCloth.sizeM, "Blue");

        ClothResponseDto responseDto = new ClothResponseDto(1L,"Shirt", EnumCloth.sizeM, "Blue");

        when(clothDtoMapper.toDomain(Mockito.any(CreateClothRequestDto.class))).thenReturn(cloth);
        when(clothService.create(Mockito.any(Cloth.class))).thenReturn(cloth);
        when(clothDtoMapper.toResponseDto(Mockito.any(Cloth.class))).thenReturn(responseDto);

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
                new Cloth(1L, "Shirt", EnumCloth.sizeM, "Blue"),
                new Cloth(1L,"Jacket", EnumCloth.sizeM, "Black")
        );

        when(clothDtoMapper.toResponseDto(any(Cloth.class)))
                .thenAnswer(invocation -> {
                    Cloth cloth = invocation.getArgument(0);
                    return new ClothResponseDto(cloth.getId(), cloth.getName(), cloth.getSize(), cloth.getColor());
                });

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
        Cloth cloth = new Cloth(id, "T-shirt", EnumCloth.sizeM, "Blue");

        ClothResponseDto responseDto = new ClothResponseDto(id, "T-shirt", EnumCloth.sizeM, "Blue");

        when(clothService.findById(id)).thenReturn(cloth);
        when(clothDtoMapper.toResponseDto(cloth))
                .thenReturn(responseDto);

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

        CreateClothRequestDto requestDto = new CreateClothRequestDto(id, "Updated Shirt", EnumCloth.sizeM, "Blue");
        Cloth updatedClothDomain = new Cloth(id, "Updated Shirt", EnumCloth.sizeM, "Blue");

        ClothResponseDto responseDto = new ClothResponseDto(id, "Updated Shirt", EnumCloth.sizeM, "Blue");

        when(clothDtoMapper.toDomain(any(CreateClothRequestDto.class))).thenReturn(updatedClothDomain);
        when(clothService.update(eq(id), any(Cloth.class))).thenReturn(Optional.of(updatedClothDomain));
        when(clothDtoMapper.toResponseDto(any(Cloth.class))).thenReturn(responseDto);

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
                new Cloth(1L, "Shirt", EnumCloth.sizeM, "Blue"),
                new Cloth(2L, "Polo", EnumCloth.sizeM, "White")
        );

        when(clothService.findBySize(EnumCloth.valueOf(size))).thenReturn(clothes);

        when(clothDtoMapper.toResponseDto(any(Cloth.class)))
                .thenAnswer(invocation -> {
                    Cloth cloth = invocation.getArgument(0);
                    return new ClothResponseDto(cloth.getId(), cloth.getName(), cloth.getSize(), cloth.getColor());
                });

        mockMvc.perform(get("/clothes/size/{size}", size)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())


                .andExpect(jsonPath("$.size()").value(clothes.size()))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Shirt"))
                .andExpect(jsonPath("$[0].color").value("Blue"))
                .andExpect(jsonPath("$[0].size").value("sizeM"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Polo"))
                .andExpect(jsonPath("$[1].color").value("White"))
                .andExpect(jsonPath("$[1].size").value("sizeM"));


        verify(clothService, times(1)).findBySize(EnumCloth.valueOf(size));
    }

}
