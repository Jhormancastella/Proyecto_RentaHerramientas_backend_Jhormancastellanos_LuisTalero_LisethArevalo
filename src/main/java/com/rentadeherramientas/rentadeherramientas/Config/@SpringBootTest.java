@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAuthentication() throws Exception {
        mockMvc.perform(post("/auth/authenticate")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"admin\",\"password\":\"admin123\"}"))
            .andExpect(status().isOk());
    }
}