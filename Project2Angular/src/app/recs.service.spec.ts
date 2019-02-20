import { TestBed } from '@angular/core/testing';

import { RecsService } from './recs.service';

describe('RecsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecsService = TestBed.get(RecsService);
    expect(service).toBeTruthy();
  });
});
