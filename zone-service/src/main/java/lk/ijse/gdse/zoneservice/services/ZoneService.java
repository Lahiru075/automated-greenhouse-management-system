package lk.ijse.gdse.zoneservice.services;

import lk.ijse.gdse.zoneservice.dto.ZoneDTO;

public interface ZoneService {
    ZoneDTO saveZone(ZoneDTO zoneDTO);

    ZoneDTO getZoneById(Long id);

    String updateZone(Long id, ZoneDTO zoneDTO);

    boolean deleteZone(Long id);
}
