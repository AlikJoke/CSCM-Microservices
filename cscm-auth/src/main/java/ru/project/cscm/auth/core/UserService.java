package ru.project.cscm.auth.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Р�РЅС‚РµСЂС„РµР№СЃ РІР·Р°РёРјРѕРґРµР№СЃС‚РІРёСЏ СЃ
 * РїРѕР»СЊР·РѕРІР°С‚РµР»СЊСЃРєРёРјРё РґР°РЅРЅС‹РјРё.
 * 
 * @author Alimurad A. Ramazanov
 *
 */
@Repository
public interface UserService extends CrudRepository<CscmUser, String> {

}
