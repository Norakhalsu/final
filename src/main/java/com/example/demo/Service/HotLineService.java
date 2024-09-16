package com.example.demo.Service;

import com.example.demo.Api.ApiException;
import com.example.demo.Model.HotLine;
import com.example.demo.Model.Requests;
import com.example.demo.Repository.HotLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HotLineService {
    private final HotLineRepository hotLineRepository;

    public List<HotLine> getHotLines() {
       return hotLineRepository.findAll();
    }
    public void addHotLineToSystem(HotLine hotLine) {
        hotLineRepository.save(hotLine);
    }

    public void updateHotLineToSystem(Integer hotlineId,HotLine hotLine) {
        HotLine hotLineToUpdate = hotLineRepository.findHotLinesByHotlineId(hotlineId);
        if(hotLineToUpdate == null) {
            throw new ApiException("hotline not found");
        }
        hotLineToUpdate.setHotLineName(hotLine.getHotLineName());
        hotLineToUpdate.setHotLineType(hotLine.getHotLineType());
        hotLineToUpdate.setDescription(hotLine.getDescription());
        hotLineToUpdate.setTitle(hotLine.getTitle());
        hotLineToUpdate.setLocation(hotLine.getLocation());
        hotLineRepository.save(hotLineToUpdate);
    }
    public void deleteHotLineFromSystem(Integer hotLineId) {
        HotLine hotLine = hotLineRepository.findHotLinesByHotlineId(hotLineId);
        if (hotLine == null) {
            throw new ApiException("HotLine Not Found in System");
        }
        hotLineRepository.delete(hotLine);
    }

    // ------------------------------ end point






}
