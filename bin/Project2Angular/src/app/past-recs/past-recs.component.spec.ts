import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PastRecsComponent } from './past-recs.component';

describe('PastRecsComponent', () => {
  let component: PastRecsComponent;
  let fixture: ComponentFixture<PastRecsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PastRecsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PastRecsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
