INSERT INTO cabal_auth_table(ID, Password, Email, Hash, Chave, Login, AuthType, Perg, IdentityNo)
VALUES (:id, HASHBYTES('SHA1 ', :password), :email, :hash, :key, :login, :authType, :perg, :identityNo);
