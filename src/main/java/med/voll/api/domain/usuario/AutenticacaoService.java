package med.voll.api.domain.usuario;


import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService  implements UserDetailsService {
    @Autowired
    private UsuarioRepository Repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Repository.findByLogin(username);
    }
}
