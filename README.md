Netbox Clone API
📖 Sobre o Projeto
Esta é uma API RESTful desenvolvida em Java com Spring Boot, inspirada na popular ferramenta de DCIM/IPAM (Data Center Infrastructure Management / IP Address Management) Netbox. O objetivo é fornecer uma versão simplificada para a documentação de infraestrutura de rede.

Este projeto foi desenvolvido como parte da disciplina de Fábrica de Software - 2025-2.

✨ Funcionalidades do Projeto
Gestão de Organização: Cadastro de local, site, grupo, etc.

Gestão de Dispositivos: Cadastro de fabricante, modelo, tipo, etc.

Gestão de Redes: Cadastro de redes, VLANs, máscaras, DNS, IP, circuitos, links, etc.

Gestão de Racks: Cadastro de rack, posicionamento de dispositivos, interfaces, etc.

Consultas: Acesso consolidado à organização, seus dispositivos, redes e racks.

Documentação: Consulta geral da documentação de rede.

📝 Histórias de Usuário
Abaixo estão as principais histórias de usuário que guiaram o desenvolvimento das funcionalidades.

História de Usuário 1 – Cadastro de Organização
Como gerente de rede, quero cadastrar informações de organização (local, site, grupo), para organizar e classificar os ativos de rede de forma hierárquica e facilitar futuras consultas.

Critérios de Aceitação:

Deve permitir informar nome, tipo (local, site ou grupo) e descrição da organização.

Deve ser possível associar sites a locais e grupos a sites.

O sistema deve validar campos obrigatórios e impedir cadastro duplicado.

História de Usuário 2 – Cadastro de Dispositivo
Como técnico de infraestrutura, desejo poder registrar dispositivos com fabricante, modelo e tipo, para manter um inventário atualizado dos equipamentos de rede.

Critérios de Aceitação:

Deve permitir selecionar o fabricante a partir de uma lista previamente cadastrada ou cadastrar um novo.

Deve permitir informar modelo, tipo (switch, roteador, servidor etc.), número de série e status (ativo/inativo).

O sistema deve permitir vincular o dispositivo a uma organização e a um rack.

História de Usuário 3 – Consulta de Documentação de Rede
Como analista de redes, preciso consultar a documentação completa de uma rede (VLANs, IPs, DNS, circuitos, links), assim tendo uma visão consolidada para facilitar manutenções e expansões da infraestrutura.

Critérios de Aceitação:

Deve ser possível buscar redes por nome, endereço IP, VLAN ou máscara.

O sistema deve exibir informações associadas: dispositivos conectados, racks e circuitos utilizados.

A consulta deve permitir exportar os resultados em PDF ou CSV.

```mermaid
classDiagram
    class Location {
        -id: Long
        -name: String
        -address: String
        +sites: List~Site~
    }

    class Site {
        -id: Long
        -name: String
        +location: Location
        +racks: List~Rack~
        +devices: List~Device~
        +vlans: List~Vlan~
    }

    class Rack {
        -id: Long
        -name: String
        -uHeight: int
        +site: Site
        +devices: List~Device~
    }

    class Vlan {
        -id: Long
        -vlanId: int
        -name: String
        +site: Site
    }

    class Manufacturer {
        -id: Long
        -name: String
        +deviceModels: List~DeviceModel~
    }

    class DeviceModel {
        -id: Long
        -name: String
        +manufacturer: Manufacturer
        +devices: List~Device~
    }

    class Device {
        -id: Long
        -name: String
        -position: int
        +site: Site
        +rack: Rack
        +deviceModel: DeviceModel
    }

    Location "1" -- "0..*" Site
    Site "1" -- "0..*" Rack
    Site "1" -- "0..*" Device
    Site "1" -- "0..*" Vlan
    Manufacturer "1" -- "0..*" DeviceModel
    DeviceModel "1" -- "0..*" Device
    Rack "1" -- "0..*" Device