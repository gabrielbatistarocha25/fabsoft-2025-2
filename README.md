Fábrica de Software - 2025-2

Sistema: Gerenciador/documentação de ativos de rede (netbox)
⦁	Funcionalidade 1:
Cadastro de organização:
Cadastro de local, site, grupo...
⦁	Funcionalidade 2:
Cadastro de dispositivos:
Cadastro de fabricante, modelo, tipo...
⦁	Funcionalidade 3:
Cadastro de redes:
Cadastro de redes,vlans, máscaras, dns, IP, circuitos, links...
⦁	Funcionalidade 4:
Cadastro de racks:
Cadastro de rack, posicionamento de dispositivos, interfaces...
⦁	Funcionalidade 5:
Consulta de organização e seus dispositivos, redes, racks...
⦁	Funcionalidade 6:
Consulta de documentação de rede

Histórias de usuário

⦁	História de Usuário 1 – Cadastro de Organização
Como gerente de rede, quero cadastrar informações de organização (local, site, grupo), para organizar e classificar os ativos de rede de forma hierárquica e facilitar futuras consultas.
Aceitação:
Deve permitir informar nome, tipo (local, site ou grupo) e descrição da organização.
Deve ser possível associar sites a locais e grupos a sites.
O sistema deve validar campos obrigatórios e impedir cadastro duplicado.
⦁	História de Usuário 2 – Cadastro de Dispositivo
Como técnico de infraestrutura, desejo poder registrar dispositivos com fabricante, modelo e tipo para manter um inventário atualizado dos equipamentos de rede.
Aceitação:
Deve permitir selecionar o fabricante a partir de uma lista previamente cadastrada ou cadastrar um novo.
Deve permitir informar modelo, tipo (switch, roteador, servidor etc.), número de série e status (ativo/inativo).
O sistema deve permitir vincular o dispositivo a uma organização e a um rack.
⦁	História de Usuário 3 – Consulta de Documentação de Rede
Como analista de redes, preciso consultar a documentação completa de uma rede (VLANs, IPs, DNS, circuitos, links), assim tendo uma visão consolidada e facilitar manutenções e expansões da infraestrutura.
Aceitação:
Deve ser possível buscar redes por nome, endereço IP, VLAN ou máscara.
O sistema deve exibir informações associadas: dispositivos conectados, racks e circuitos utilizados.
A consulta deve permitir exportar os resultados em PDF ou CSV.