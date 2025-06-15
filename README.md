# SurveyList

Este projeto é uma aplicação Android nativa que exibe uma lista performática e acessível de pesquisas para o usuário responder.

---

#### Algumas screenshots do aplicativo:
![image](https://github.com/user-attachments/assets/d0dcd705-c519-40ac-bdee-185eb24c7c12)
![image](https://github.com/user-attachments/assets/5c2d0645-4b66-4ffb-ba83-a868af78c4cf)
![image](https://github.com/user-attachments/assets/7a156d27-aa55-4e86-a4da-3c28771231d0)
![image](https://github.com/user-attachments/assets/42c326d8-6b2f-486b-a705-cedfe9076e5f)


## Funcionalidades

- Listagem performática com `RecyclerView` e lazy loading (item loading) 
- Suporte a acessibilidade: descrições para TalkBack, foco, botões clicáveis 
- Suporte a temas escuro/claro automático conforme o tema do dispositivo 
- Layout responsivo e adaptável para diferentes tamanhos de tela 
- Uso de dimensões em `sp` para textos, respeitando preferências do usuário 
- Botão "Responder" para cada pesquisa que exibe um BottomSheet (popup) de início da pesquisa

---

## Como executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/pinhothiago/survey-list.git
   cd survey-list

2. Abra o projeto no Android Studio (versão recomendada 2023.1+)
3. Build e rode em um emulador ou dispositivo real com Android 7.0+ (API 24+)

### Detalhes técnicos

- Implementação de item de loading com view nula para simular paginação e carregamento lazy.

#### Tema escuro/claro automático
O projeto utiliza o tema:

 ```bash
<style name="Theme.SurveyList" parent="Theme.MaterialComponents.DayNight.NoActionBar"/>
```
Este tema DayNight do Material Components responde automaticamente às configurações do sistema Android:

- Se o dispositivo estiver no modo claro, o tema claro é aplicado
- Se o dispositivo estiver no modo escuro, o tema escuro é aplicado

Isso dispensa qualquer lógica manual no código para troca de tema, tornando a experiência natural para o usuário.

### Acessibilidade
- Todos os elementos interativos possuem contentDescription para leitores de tela (TalkBack/VoiceOver).

### Performance
- RecyclerView é usado com ViewHolder padrão para garantir scroll fluido mesmo em dispositivos mais modestos.

### Estrutura do projeto
- Survey — Model de dados da pesquisa
- SurveyAdapter — Adapter do RecyclerView, com suporte a dois tipos de itens (pesquisa e loading)
- MainActivity — Tela principal que exibe a lista
- MockData — Dados mockados para teste da lista
- DelayHandler — Interface para simular delay em carregamento (usada na paginação)
- Paginator — Classe para simular paginação lazy loading
- SurveyStartBottomSheet - Pop-up para dar início a pesquisa, com ações de cancelar ou começar.

### Testes
- Testes unitários para o adapter (SurveyAdapterTest) verificando contagem e tipos de itens
- Testes para o paginador (PaginatorTest) garantindo carregamento incremental correto

