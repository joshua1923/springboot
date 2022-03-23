package dae.mn.springbootgraphqlengframework.resolvers;

import dae.mn.springbootgraphqlengframework.comment.CommentResource;
import dae.mn.springbootgraphqlengframework.post.PostResource;
import dae.mn.springbootgraphqlengframework.user.UserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

class MutationResolverTest {

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;
    @Mock
    private UserResource userResource;
    @Mock
    private PostResource postResource;
    @Mock
    private CommentResource commentResource;

    private MutationResolver mutationResolverUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mutationResolverUnderTest = new MutationResolver(userResource, postResource, commentResource);
    }

    @Test
    void itShouldCreateUser() {
        // Given
        String email = "test@email.com";
        String userName = "Johnny Test";
        String password = "johnnysdateofbirth";
        List<String> arguments = List.of(email, userName, password);

        // When
        mutationResolverUnderTest.createUser(email, userName, password);

        // Then
        then(userResource).should().createUser(stringArgumentCaptor.capture(), stringArgumentCaptor.capture(), stringArgumentCaptor.capture());
        var argumentCaptures = stringArgumentCaptor.getAllValues();

        for (int i = 0; i < arguments.size(); i++)
            assertThat(argumentCaptures.get(i)).isEqualTo(arguments.get(i));
    }


}