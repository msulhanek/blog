package sk.posam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.blog.dto.UserDto;
import sk.posam.blog.exception.PostNotFondException;
import sk.posam.blog.exception.UserNotFoundException;
import sk.posam.blog.model.Post;
import sk.posam.blog.model.User;
import sk.posam.blog.repository.PostRepository;
import sk.posam.blog.repository.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    public Object getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapFromUserToUserDto).collect(toList());
    }

    private UserDto mapFromUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurename(user.getSurename());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRegisterDate(user.getRegisterDate());
        userDto.setProfilePicture(user.getProfilePicture());
        userDto.setNote(user.getNote());
        userDto.setNumberOfPosts(user.getNumberOfPosts());
        return userDto;
    }

    public Object getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new UserNotFoundException("For username: " + username));
        return mapFromUserToUserDto(user);
    }

    public Object editUser(String username, UserDto userDto) {
        List<Post> posts = postRepository.findByAuthor(username);
        for (Post post: posts) {
            post.setAuthor(userDto.getUsername());
            postRepository.save(post);
        }

        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new UserNotFoundException("For username: " + username));
        user.setName(userDto.getName());
        user.setSurename(userDto.getSurename());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setProfilePicture(userDto.getProfilePicture());
        user.setNote(userDto.getNote());
        userRepository.save(user);
        return user;
    }
}
