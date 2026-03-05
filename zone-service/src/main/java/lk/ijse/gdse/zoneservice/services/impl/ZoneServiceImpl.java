package lk.ijse.gdse.zoneservice.services.impl;

import lk.ijse.gdse.zoneservice.dto.ZoneDTO;
import lk.ijse.gdse.zoneservice.entity.Zone;
import lk.ijse.gdse.zoneservice.repository.ZoneRepository;
import lk.ijse.gdse.zoneservice.services.ZoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ZoneDTO saveZone(ZoneDTO zoneDTO) {

        // zoneDTO.setDeviceId(); in the future

        if (zoneDTO.getName() == null || zoneDTO.getName().isEmpty() || zoneDTO.getDeviceId() == null || zoneDTO.getDeviceId().isEmpty() ) {
            throw new IllegalArgumentException("Zone name or deviceId cannot be null or empty");
        }

        if (zoneDTO.getMinTemp() >= zoneDTO.getMaxTemp()) {
            throw new IllegalArgumentException("Minimum temperature must be strictly less than maximum temperature");
        }

        Zone zone = modelMapper.map(zoneDTO, Zone.class);

        zoneRepository.save(zone);

        return modelMapper.map(zone, ZoneDTO.class);

    }

    @Override
    public ZoneDTO getZoneById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid zone ID");
        }

        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zone not found with ID: " + id));

        return modelMapper.map(zone, ZoneDTO.class);
    }

    @Override
    public String updateZone(Long id, ZoneDTO zoneDTO) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid zone ID");
        }

        if (zoneDTO.getName() == null || zoneDTO.getName().isEmpty() || zoneDTO.getDeviceId() == null || zoneDTO.getDeviceId().isEmpty() ) {
            throw new IllegalArgumentException("Zone name or deviceId cannot be null or empty");
        }

        if (zoneDTO.getMinTemp() >= zoneDTO.getMaxTemp()) {
            throw new IllegalArgumentException("Minimum temperature must be strictly less than maximum temperature");
        }

        Zone existingZone = zoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zone not found with ID: " + id));

        existingZone.setName(zoneDTO.getName());
        existingZone.setMinTemp(zoneDTO.getMinTemp());
        existingZone.setMaxTemp(zoneDTO.getMaxTemp());
        existingZone.setDeviceId(zoneDTO.getDeviceId());

        zoneRepository.save(existingZone);

        return "Zone updated successfully";
    }

    @Override
    public boolean deleteZone(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid zone ID");
        }

        if (!zoneRepository.existsById(id)) {
            throw new IllegalArgumentException("Zone not found with ID: " + id);
        }

        zoneRepository.deleteById(id);
        return true;
    }
}
